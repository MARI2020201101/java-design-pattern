package builder;

class ChocoV2 {
    int cacao;
    boolean isNatural;
    protected ChocoV2(){}

    protected ChocoV2(ChocoBuilder chocoBuilder){
        cacao=chocoBuilder.choco.cacao;
        isNatural=chocoBuilder.choco.isNatural;
    }
    protected static ChocoBuilder builder(){
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
        protected ChocoV2 choco = new ChocoV2();

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
        public ChocoV2 build(){
            return choco;
        }
    }
}

class ChocoBuilderDemoV2{
    public static void main(String[] args) {
        ChocoV2 choco = ChocoV2.builder()
                .cacao(100)
                .isNatural(true)
                .build();

        System.out.println(choco);
    }
}