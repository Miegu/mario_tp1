package tp1.logic;

import tp1.logic.Action;
import java.util.ArrayList;
import java.util.List;

public class ActionList {

    private List<Action> actions = new ArrayList<>();

    public void add(Action act) { //una
        actions.add(act);
    }

    public void add(List<Action> acts) { // varias
        // actions.addAll(acts);
        List<Action> filtered = new ArrayList<>();

        boolean hasLeft = false;
        boolean hasRight = false;
        boolean hasUp = false;
        boolean hasDown = false;
        int horizontalCount = 0;
        int verticalCount = 0;

        for (Action a : acts) {
            switch (a) {
                case LEFT:
                    if (!hasRight && horizontalCount < 4) {
                        filtered.add(a);
                        hasLeft = true;
                        horizontalCount++;
                    }
                    break;
                case RIGHT:
                    if (!hasLeft && horizontalCount < 4) {
                        filtered.add(a);
                        hasRight = true;
                        horizontalCount++;
                    }
                    break;
                case UP:
                    if (!hasDown && verticalCount < 4) {
                        filtered.add(a);
                        hasUp = true;
                        verticalCount++;
                    }
                    break;
                case DOWN:
                    if (!hasUp && verticalCount < 4) {
                        filtered.add(a);
                        hasDown = true;
                        verticalCount++;
                    }
                    break;
                case STOP:
                    if (!hasLeft && !hasRight) {
                        filtered.add(a);
                    }
                    break;
            }
        }

        this.actions.addAll(filtered);
    }

    public Action extract() { //quita y devuelve primera del array
        if (actions.isEmpty()) return null;
        return actions.remove(0);
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public void clear() {
        actions.clear();
    }

    public int size() {
        return actions.size();
    }

    @Override
    public String toString() {
        return actions.toString();
    }
}
