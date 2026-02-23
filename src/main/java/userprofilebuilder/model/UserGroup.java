/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userprofilebuilder.model;

/**
 *
 * @author 
 * This is a model class that may be handy for putting data relevant groups of User Profiles in {many Users]
 */
public final class UserGroup {
    
    private static UserGroup instance;
    
    private UserGroup() {
        
    }
    
    public static UserGroup getInstance() {
        if (instance == null) {
            instance = new UserGroup();
        }
        return instance;
    }
}
