package org.objectstyle.linkrest.cms.cayenne.auto;

import java.util.Date;
import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;
import org.objectstyle.linkrest.cms.cayenne.Domain;
import org.objectstyle.linkrest.cms.cayenne.Tag;

/**
 * Class _Article was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Article extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    @Deprecated
    public static final String BODY_PROPERTY = "body";
    @Deprecated
    public static final String PUBLISHED_ON_PROPERTY = "publishedOn";
    @Deprecated
    public static final String TITLE_PROPERTY = "title";
    @Deprecated
    public static final String DOMAIN_PROPERTY = "domain";
    @Deprecated
    public static final String TAGS_PROPERTY = "tags";

    public static final String ID_PK_COLUMN = "id";

    public static final Property<String> BODY = new Property<String>("body");
    public static final Property<Date> PUBLISHED_ON = new Property<Date>("publishedOn");
    public static final Property<String> TITLE = new Property<String>("title");
    public static final Property<Domain> DOMAIN = new Property<Domain>("domain");
    public static final Property<List<Tag>> TAGS = new Property<List<Tag>>("tags");

    public void setBody(String body) {
        writeProperty("body", body);
    }
    public String getBody() {
        return (String)readProperty("body");
    }

    public void setPublishedOn(Date publishedOn) {
        writeProperty("publishedOn", publishedOn);
    }
    public Date getPublishedOn() {
        return (Date)readProperty("publishedOn");
    }

    public void setTitle(String title) {
        writeProperty("title", title);
    }
    public String getTitle() {
        return (String)readProperty("title");
    }

    public void setDomain(Domain domain) {
        setToOneTarget("domain", domain, true);
    }

    public Domain getDomain() {
        return (Domain)readProperty("domain");
    }


    public void addToTags(Tag obj) {
        addToManyTarget("tags", obj, true);
    }
    public void removeFromTags(Tag obj) {
        removeToManyTarget("tags", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Tag> getTags() {
        return (List<Tag>)readProperty("tags");
    }


    protected abstract void onPostAdd();

}
