package ua.lviv.office.constans;


public final class UserConstants {


  private UserConstants() {
  }



  public static final class Entity {

    public static final String TABLE_NAME_USER = "users";
    public static final String ID_USER = "id_user";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone_number";
    public static final String ROLE = "role";
    public static final String USER = "user";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String PROFILE_IMG = "profile_image";
    public static final String IS_RORE_CONFIRMED = "is_role_confirmed";
    private Entity() {
    }
  }


  public static final class Role {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";

    private Role() {
    }
  }
}
