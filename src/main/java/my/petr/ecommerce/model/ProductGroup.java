package my.petr.ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_groups")
@Data
@NoArgsConstructor
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_name")
    private String groupName;
    private String price;
    private String created;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupVariant> groupVariants = new ArrayList<>();
}
