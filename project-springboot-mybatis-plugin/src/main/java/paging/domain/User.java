package paging.domain;


import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xujiashuai on 2016/6/18.
 */
@Table(name="users")
public class User {

    @Id
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
