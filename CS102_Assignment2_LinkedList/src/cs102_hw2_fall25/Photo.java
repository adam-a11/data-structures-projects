package cs102_hw2_fall25;

public class Photo {
    private String name;        // name of the photo
    private String photoDigest; // unique string identifier
    private boolean viewed;     // whether this photo has been viewed

    // constructor
    public Photo(String name, String photoDigest) {
        this.name = name;
        this.photoDigest = photoDigest;
        this.viewed = false;
    }

    // view a photo
    public void viewPhoto() {
        System.out.println("Now viewing " + name + ".");
        viewed = true;
    }

    public boolean isViewed() {
        return viewed;
    }

    public String getName() {
        return name;
    }

    public String getPhotoDigest() {
        return photoDigest;
    }

    // equals based on photoDigest
    public boolean equals(Photo other) {
        if (other == null) return false;
        return this.photoDigest.equals(other.photoDigest);
    }
}
