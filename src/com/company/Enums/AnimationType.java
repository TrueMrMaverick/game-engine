package com.company.Enums;

/**
 * Created by Nikita on 06.01.2018.
 */
public enum AnimationType {
    MOVE,
    SCALE,
    FIXED_TURN,
    FORWARD_TURN;

    public static AnimationType intToAnimationType(int i){
        switch (i){
            case 1:
                return AnimationType.MOVE;
            case 2:
                return AnimationType.SCALE;
            case 3:
                return AnimationType.FIXED_TURN;
            case 4:
                return AnimationType.FORWARD_TURN;
        }
        return null;
    }
}
