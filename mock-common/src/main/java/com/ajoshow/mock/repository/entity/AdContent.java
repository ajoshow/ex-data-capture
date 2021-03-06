package com.ajoshow.mock.repository.entity;

import com.ajoshow.mock.domain.Link;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by andychu on 2017/4/22.
 */
@Entity
@Table(name = "ad_content",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_title", columnNames="title"),
        }
)
public class AdContent implements Serializable {

    private Integer id;
    private String title;
    private Set<String> impressionEvent;
    private Set<String> viewEvent;
    private List<Asset> assets;
    private Link link;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="AD_CONTENT_ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "ad_content_impression_url",
            joinColumns = {
                    @JoinColumn(name = "AD_CONTENT_ID", referencedColumnName = "AD_CONTENT_ID", nullable = false)
            },
            foreignKey = @ForeignKey(name = "fk_impression_url")
    )
    @Column(name="impression_url", length = 1000, nullable = false)
    public Set<String> getImpressionEvent() {
        return impressionEvent;
    }

    public void setImpressionEvent(Set<String> impressionEvent) {
        this.impressionEvent = impressionEvent;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "ad_content_view_url",
            joinColumns = {
                    @JoinColumn(name = "AD_CONTENT_ID", referencedColumnName = "AD_CONTENT_ID", nullable = false)
            },
            foreignKey = @ForeignKey(name = "fk_view_url")
    )
    @Column(name="view_url", length = 1000, nullable = false)
    public Set<String> getViewEvent() {
        return viewEvent;
    }

    public void setViewEvent(Set<String> viewEvent) {
        this.viewEvent = viewEvent;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "AD_CONTENT_ID", referencedColumnName = "AD_CONTENT_ID")
    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @Embedded
    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
