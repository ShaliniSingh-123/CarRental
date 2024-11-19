public class SliderItem {
    private String hostedBy;
    private String carDetails;
    private String reviewerName;
    private String reviewText;
    private int imageResourceId;

    public SliderItem(String hostedBy, String carDetails, String reviewerName, String reviewText, int imageResourceId) {
        this.hostedBy = hostedBy;
        this.carDetails = carDetails;
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        this.imageResourceId = imageResourceId;
    }

    public String getHostedBy() {
        return hostedBy;
    }

    public String getCarDetails() {
        return carDetails;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
