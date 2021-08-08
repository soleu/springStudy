package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements  MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public  //생성자가 1개만 있으면 Autowired 생략 가능
    JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("name",member.getName());

        Number key= jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result =jdbcTemplate.query("select * from member where id= ? ",memberRowMapper(),id);
                //결과값을 rowMapper로 매핑
        return result.stream().findAny(); //List -> Optional
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =jdbcTemplate.query("select * from member where name= ? ",memberRowMapper(),name);
        return result.stream().findAny(); //List -> Optional
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from memer",memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs,rowNum) -> {
            Member member=new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
