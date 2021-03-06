package rbac.dao.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;
import org.joda.time.DateTime;
import rbac.utils.RandomUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pandaking on 2017/6/1.
 */
@Setter
@Getter
@MappedSuperclass
@FilterDefs({
        /**
         * @See rbac.web.aop.JpaDaoAspect
         */
        @FilterDef(name = "DepartmentFilter", defaultCondition = "creator in (:creator)",parameters = @ParamDef(name = "creator", type = "long"))
})
@Filter(name = "DepartmentFilter")
public class BaseEntity {

    //菜单表
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uuid", updatable = false)
    private String uuid = RandomUtil.getCode(64, 5);

    @Column(name = "creator", updatable = false)
    private Long creator;

    @Column(name = "editor")
    private Long editor;

    @Column(name = "create_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = DateTime.now().toDate();

    @Column(name = "edit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editTime = DateTime.now().toDate();
}
