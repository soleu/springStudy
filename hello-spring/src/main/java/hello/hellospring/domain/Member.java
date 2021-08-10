package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    //DB가 알아서 값을 생성해주는 전략 = IDENTITY
    //여기서는 IDENTITY로 id값을 자동으로 할당 받고 있음
    private Long id;

    //@Column(name="username") : 이런식으로 db에서 사용하는 이름으로 mapping 시킬 수 있음
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
