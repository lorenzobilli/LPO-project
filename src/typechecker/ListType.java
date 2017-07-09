package typechecker;

/**
 * ListType class
 */
public class ListType implements Type {

    public static final String LIST = "LIST";
    private final Type listType;

    public ListType(Type listType) {
        this.listType = listType;
    }

    public Type getListType() {
        return listType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ListType)) {
            return false;
        }
        ListType list = (ListType) object;
        return listType.equals(list.listType);
    }

    @Override
    public int hashCode() {
        return 31 * listType.hashCode();
    }

    @Override
    public String toString() {
        return listType + " " + LIST;
    }
}
