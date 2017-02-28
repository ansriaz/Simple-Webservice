package waes.assignment;

public class RestResponse {

    private final long id;
    private final String result;
    private final String left;
    private final String right;

    public RestResponse(long id, String left, String right, String result) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public String getResult() {
        return result;
    }
    
    public String getLeft() {
        return left;
    }
    
    public String getRight() {
        return right;
    }
}
