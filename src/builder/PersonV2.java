package builder;

class PersonV2 {
    public int annualIncome;
    public String streetAddress, postcode, city;
    public String companyName, position;

    @Override
    public String toString() {
        return "Person2{" +
                "annualIncome=" + annualIncome +
                ", streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
class PersonJobBuilder extends PersonBuilderV2{
    public PersonJobBuilder(PersonV2 person) {
        this.person = person;
    }

    public PersonJobBuilder income(int annualIncome){
        person.annualIncome = annualIncome;
        return this;
    }
    public PersonJobBuilder companyName(String companyName){
        person.companyName = companyName;
        return this;
    }
    public PersonJobBuilder position(String position){
        person.position = position;
        return this;
    }
}
class PersonAddressBuilder extends PersonBuilderV2{
    public PersonAddressBuilder(PersonV2 person) {
        this.person = person;
    }
    public PersonAddressBuilder at(String streetaddress){
        person.streetAddress = streetaddress;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode){
        person.postcode = postcode;
        return this;
    }
    public PersonAddressBuilder in(String city){
        person.city = city;
        return this;
    }
}
class PersonBuilderV2{
    protected PersonV2 person = new PersonV2();

    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(person);
    }
    public PersonJobBuilder works(){
        return new PersonJobBuilder(person);
    }
    public PersonV2 build(){
        return person;
    }
}
class Demo4{
    public static void main(String[] args) {
        PersonBuilderV2 pb = new PersonBuilderV2();

        PersonV2 personV2 = pb
                .lives()
                    .at("street-01")
                    .in("London")
                    .withPostcode("222-1234")
                .works()
                    .companyName("Samsung")
                    .income(10000000)
                    .position("Developer")
                .build();

        System.out.println(personV2);

    }
}