package sample.domain;

public class Pipeline<I, O> {
   private final Step<I, O> current;
   public Pipeline(Step<I, O> current) {
      this.current = current;
   }

   public <NewO> Pipeline<I, NewO> pipe(Step<O, NewO> next) {
      return new Pipeline<>(new Step<>() {
         @Override
         public NewO process(I in) {
            return next.process(current.process(in));
         }
      });
   }

   public O execute(I input) {
      return current.process(input);
   }
}