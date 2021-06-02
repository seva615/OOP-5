package sample.domain;

public abstract class Step<I extends Object,O> {
   public abstract O process(I in);

}
