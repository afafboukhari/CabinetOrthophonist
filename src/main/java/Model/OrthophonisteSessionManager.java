package Model;

public class OrthophonisteSessionManager
{
    private  static Orthophonist currentOrthophoniste;

    private OrthophonisteSessionManager() {}



    public  static Orthophonist getCurrentOrthophonisteName()
    {
        return currentOrthophoniste;
    }

    public  static  void setCurrentOrthophonisteName(Orthophonist orthophonist) {
        currentOrthophoniste = orthophonist;
    }
}
