package institute_tasks._CourseWork;

public class Client {
        private String accommodationType;
        private String foodPreference;
        private String locationPreference;
        private int durationOfStay;

        public Client(String accommodationType, String foodPreference, String locationPreference, int durationOfStay) {
            this.accommodationType = accommodationType;
            this.foodPreference = foodPreference;
            this.locationPreference = locationPreference;
            this.durationOfStay = durationOfStay;
        }

        // Геттеры и сеттеры
        public String getAccommodationType() {
            return accommodationType;
        }

        public void setAccommodationType(String accommodationType) {
            this.accommodationType = accommodationType;
        }

        public String getFoodPreference() {
            return foodPreference;
        }

        public void setFoodPreference(String foodPreference) {
            this.foodPreference = foodPreference;
        }

        public String getLocationPreference() {
            return locationPreference;
        }

        public void setLocationPreference(String locationPreference) {
            this.locationPreference = locationPreference;
        }

        public int getDurationOfStay() {
            return durationOfStay;
        }

        public void setDurationOfStay(int durationOfStay) {
            this.durationOfStay = durationOfStay;
        }
    }
