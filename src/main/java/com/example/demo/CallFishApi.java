public class CallFishApi{

  public String[] closeFish(){
    String[] listOfFish = {"torsk","rødspætte"};

    return listOfFish;
  }

  public Fish specificFish(){
    Fish fundetFisk = new Fish("gedde");

    return fundetFisk;
  }

  class Fish{
    String navn;

    public Fish(String fiskeNavn){
      navn = fiskeNavn;
    }
  }
}
