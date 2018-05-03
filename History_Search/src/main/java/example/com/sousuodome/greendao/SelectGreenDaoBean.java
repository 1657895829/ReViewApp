package example.com.sousuodome.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lenovo on 2018/4/28.
 */
@Entity
public class SelectGreenDaoBean {
    @Id(autoincrement = true)
    private Long id;
    private String uid;
    private String uname;
    private String selectGoods;
    @Generated(hash = 1175471036)
    public SelectGreenDaoBean(Long id, String uid, String uname,
            String selectGoods) {
        this.id = id;
        this.uid = uid;
        this.uname = uname;
        this.selectGoods = selectGoods;
    }
    @Generated(hash = 836195850)
    public SelectGreenDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUname() {
        return this.uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getSelectGoods() {
        return this.selectGoods;
    }
    public void setSelectGoods(String selectGoods) {
        this.selectGoods = selectGoods;
    }
}
