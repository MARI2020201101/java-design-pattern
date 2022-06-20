package composite;

import java.util.*;
import java.util.function.Consumer;

class Exercise {
}

interface ValueContainer extends Iterable<Integer> {}

class SingleValue implements ValueContainer {
    public int value;
    public SingleValue(int value) {
        this.value = value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Collections.singleton(this.value).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        action.accept(this.value);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Collections.singleton(this.value).spliterator();
    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer {
}

class MyList extends ArrayList<ValueContainer> {
    public MyList(Collection<? extends ValueContainer> c) {
        super(c);
    }
    public int sum() {
        int result = 0;
        for (ValueContainer valueContainer : this) {
            for (Integer integer : valueContainer) {
                result += integer;
            }
        }
        return result;
    }
}

class Demo3{
    public static void main(String[] args) {
        SingleValue singleValue = new SingleValue(1);
        ManyValues manyValues = new ManyValues();
        manyValues.add(2);
        manyValues.add(3);
        manyValues.add(4);
        manyValues.add(5);

        List<ValueContainer> lists = new ArrayList<>();
        lists.add(singleValue);
        lists.add(manyValues);
        MyList myList = new MyList(lists);
        System.out.println("sum result : " + myList.sum());
    }
}