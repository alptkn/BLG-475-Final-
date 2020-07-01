package configs;

public class InternalProps extends AbstractProps
{
    private String username;
    private String password;
    private String phone_number;
    private String invalid_phone_number;
    private String incomplete_phone_number;


    public InternalProps()
    {
        loadConfigProperties("internal_config.properties");

        this.username = configProps.getProperty("user.name");
        this.password = configProps.getProperty("user.password");
        this.phone_number = configProps.getProperty("user.phone_number");
        this.invalid_phone_number = configProps.getProperty("user.invalid_phone_number");
        this.incomplete_phone_number = configProps.getProperty("user.incomplete_phone_number");
    }

    //Getters and Setters


    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhone_number() {return phone_number;}

    public String getInvalidPhoneNumber() {return this.invalid_phone_number;}

    public String getIncompletePhoneNumber(){return this.incomplete_phone_number;};
}
