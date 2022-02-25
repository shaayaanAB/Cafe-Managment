package org.bihe.bean;

import java.util.ArrayList;

public class BadgeManager {

    private ArrayList<Badge> badges;

    BadgeManager() {
        this.badges = new ArrayList<>();
    }
//    ArrayList<Badge> getBadges() {
//        return badges;
//    }

    Badge createBadge(String name, int offPercent) {
        Badge newBadge = new Badge(name, offPercent);
        // check for not adding repeated badge
        if (isBadgeUnique(name)) {
            this.badges.add(newBadge);
            return newBadge;
        }
        return null;
    }

    private boolean isBadgeUnique(String newBadgeName) {
        for (Badge badge : this.badges) {
            if (badge.getName().equals(newBadgeName)) {
                return false;
            }
        }
        return true;
    }

    Badge findBadge(String badgeName) {
        for (Badge badge : this.badges) {
            if (badge.getName().equals(badgeName)) {
                return badge;
            }
        }
        return null;
    }

}
