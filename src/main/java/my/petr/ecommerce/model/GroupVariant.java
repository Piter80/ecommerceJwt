package my.petr.ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "group_variants")
@Data
@NoArgsConstructor
public class GroupVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "variant_name")
    private String variantName;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductGroup group;

}
