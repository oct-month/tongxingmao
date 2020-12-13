package top.ablocker.tongxingmao.entity;

public class TAG
{
    private String name;
    private String describ;

    public TAG()
    {
    }

    public TAG(String name, String describ)
    {
        this.name = name;
        this.describ = describ;
    }

    public String getName()
    {
        return name;
    }

    public String getDescrib()
    {
        return describ;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescrib(String describ)
    {
        this.describ = describ;
    }
}
