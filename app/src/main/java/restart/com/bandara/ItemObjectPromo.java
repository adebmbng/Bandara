package restart.com.bandara;

/**
 * Created by lenovo on 2/16/2017.
 */

public class ItemObjectPromo {
    private String name;
    private int photo;

    public ItemObjectPromo(String name, int photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}

