package builder;

class Choco {
    int cacao;
    boolean isNatural;

    public Choco(){}
    public Choco(int cacao, boolean isNatural) {
        this.cacao = cacao;
        this.isNatural = isNatural;
    }
    public static ChocoBuilder builder(){
        return new ChocoBuilder();
    }

    @Override
    public String toString() {
        return "Choco{" +
                "cacao=" + cacao +
                ", isNatural=" + isNatural +
                '}';
    }

    static class ChocoBuilder<SELF extends ChocoBuilder<SELF>>{
        protected Choco choco = new Choco();

        public ChocoBuilder(){}

        public SELF cacao(int cacao){
            choco.cacao = cacao;
            return self();
        }
        public SELF isNatural(boolean isNatural){
            choco.isNatural = isNatural;
            return self();
        }
        protected SELF self(){
            return (SELF) this;
        }
        public Choco build(){
            return choco;
        }
    }
}
class ChocoBuilderDemo{
    public static void main(String[] args) {
        Choco choco = Choco.builder()
                .cacao(100)
                .isNatural(true)
                .build();

        System.out.println(choco);
    }
}