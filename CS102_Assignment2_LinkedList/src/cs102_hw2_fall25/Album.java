package cs102_hw2_fall25;

public class Album implements IAlbum {
    private String albumName;
    private Node current;
    private int count;
    private boolean open;

    // inner node class
    private class Node {
        Photo photo;
        Node next;
        Node prev;

        Node(Photo p) {
            this.photo = p;
        }
    }

    public Album(String name) {
        this.albumName = name;
        this.current = null;
        this.count = 0;
        this.open = false;
    }

    @Override
    public String getAlbumName() {
        return albumName;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean hasPhoto(Photo photo) {
        if (current == null) return false;
        Node temp = current;
        for (int i = 0; i < count; i++) {
            if (temp.photo.equals(photo)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public void addPhoto(Photo photo) {
        if (photo == null) return;

        // check for duplicate
        if (hasPhoto(photo)) {
            System.out.println("Attempted to add duplicate photo.");
            return;
        }

        Node newNode = new Node(photo);
        if (current == null) {
            // first photo
            current = newNode;
            current.next = current;
            current.prev = current;
        } else {
            // add before current (circular list)
            Node last = current.prev;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = current;
            current.prev = newNode;
        }
        count++;
    }

    @Override
    public void deletePhoto(Photo photo) {
        if (current == null) {
            System.out.println("Attempted to delete a photo that is not in the album.");
            return;
        }

        Node temp = current;
        for (int i = 0; i < count; i++) {
            if (temp.photo.equals(photo)) {
                if (count == 1) {
                    current = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                    if (temp == current) {
                        current = temp.next;
                    }
                }
                count--;
                return;
            }
            temp = temp.next;
        }
        // not found
        System.out.println("Attempted to delete a photo that is not in the album.");
    }

    @Override
    public void openAlbum() {
        System.out.println("Album " + albumName + " opened.");
        open = true;
        if (count > 0 && current != null) {
            current.photo.viewPhoto(); // mark current photo as viewed
        }
    }

    @Override
    public void closeAlbum() {
        System.out.println("Album " + albumName + " closed.");
        open = false;
    }

    @Override
    public void viewNextPhoto() {
        if (!open) {
            System.out.println("Tried to view next photo, but album is closed.");
            return;
        }
        if (count == 0) {
            System.out.println("Tried to view next photo, but album has no photos.");
            return;
        }
        current = current.next;
        current.photo.viewPhoto();
    }

    @Override
    public void viewPreviousPhoto() {
        if (!open) {
            System.out.println("Tried to view previous photo, but album is closed.");
            return;
        }
        if (count == 0) {
            System.out.println("Tried to view previous photo, but album has no photos.");
            return;
        }
        current = current.prev;
        current.photo.viewPhoto();
    }

    @Override
    public boolean allPhotosViewed() {
        if (current == null) return true;
        Node temp = current;
        for (int i = 0; i < count; i++) {
            if (!temp.photo.isViewed()) return false;
            temp = temp.next;
        }
        return true;
    }

    @Override
    public boolean equals(IAlbum other) {
        if (other == null) return false;
        if (this.count != other.getCount()) return false;

        Node temp = current;
        for (int i = 0; i < count; i++) {
            if (!other.hasPhoto(temp.photo)) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    // optional helper
    public boolean isEmpty() {
        return count == 0;
    }

    // simple test
    public static void main(String[] args) {
    	System.out.println("/|\\ /|\\ /|\\ /|\\ /|\\ /|\\");
    	System.out.println("\nSample output 6\n");
    	System.out.println("Testing edge cases");
    	Album album = new Album("My Cool Album");
    	Photo photo1 = new Photo("Sunset", "abc123");
    	Photo photo2 = new Photo("Sunrise", "def456");
    	Photo photo3 = new Photo("Can of Beans", "ghi789");
    	album.addPhoto(photo1);
    	album.addPhoto(photo2);
    	album.addPhoto(photo3);
    	System.out.println("\nDeleting current photo should make next photo current:");
    	album.openAlbum();
    	album.viewNextPhoto();
    	album.closeAlbum();
    	album.deletePhoto(photo2);
    	album.openAlbum();
    	album.closeAlbum();
    	album.deletePhoto(photo3);
    	album.openAlbum();
    	album.closeAlbum();
    	System.out.println("\nAlbum with no photos should be considered viewed:");
    	album.deletePhoto(photo1);
    	System.out.println("Is album empty? " + album.isEmpty());
    	System.out.println("Are all photos in album viewed? " + album.allPhotosViewed());
    	System.out.println("\nAlbum that is viewed should become unviewed once new photo is added:");
    	album.addPhoto(new Photo("Shredding the Gnar", "012345"));
    	album.openAlbum();
    	System.out.println("Are all photos in album viewed? " + album.allPhotosViewed());
    	album.addPhoto(new Photo("Hanging Ten", "678901"));
    	System.out.println("Are all photos in album viewed? " + album.allPhotosViewed());
    	System.out.println("\n\\|/ \\|/ \\|/ \\|/ \\|/ \\|/");




    }
}
