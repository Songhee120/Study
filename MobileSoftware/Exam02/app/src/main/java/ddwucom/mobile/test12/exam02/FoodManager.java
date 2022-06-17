package ddwucom.mobile.test12.exam02;

import java.util.ArrayList;

public class FoodManager {

    private ArrayList<Food> foodList;

    public FoodManager() {
        foodList = new ArrayList<>();
        foodList.add(new Food("김치찌개", "한국"));
        foodList.add(new Food("된장찌개", "한국"));
        foodList.add(new Food("훠궈", "중국"));
        foodList.add(new Food("딤섬", "중국"));
        foodList.add(new Food("초밥", "일본"));
        foodList.add(new Food("오코노미야키", "일본"));
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public void removeFood(int index) {
        foodList.remove(index);
    }

    public void modifyFood(int index, Food food) {
        foodList.set(index, food);
    }
}
