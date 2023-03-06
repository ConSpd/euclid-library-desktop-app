package euclid;

public class Results{
    private int number;
    private String name;
    private String author;
    private String publisher = "Unknown_publisher";
    private String year = "Unknown_year";
    private String category = "";
    
    private Results(ResultsBuilder builder){
        this.number = builder.number;
        this.name = builder.name;
        this.author = builder.author;
        this.publisher = builder.publisher;
        this.year = builder.year;
        this.category = builder.category;
    }
    
    public int getNumber(){
        return this.number;
    }

    public String getName(){
        return this.name;
    }
    
    public String getAuthor(){
        return this.author;
    }
    
    public String getPublisher(){
        return this.publisher;
    }
    
    public String getYear(){
        return this.year;
    }
    
    public String getCategory(){
        return this.category;
    }
    
    @Override
    public String toString(){
        String result = "Number: "+this.number+
                        " Name: "+this.name+
                        " Author: "+this.author+
                        " Publisher: "+this.publisher+
                        " Year: "+this.year+
                        " Category: "+this.category;
        return result;
    }
    
    public static class ResultsBuilder{
        private int number;
        private String name;
        private String author;
        private String publisher;
        private String year;
        private String category = "";

        public ResultsBuilder() { }

        public ResultsBuilder number(int number){
            this.number = number;
            return this;
        }

        public ResultsBuilder name(String name){
            this.name = name;
            return this;
        }

        public ResultsBuilder author(String author){
            this.author = author;
            return this;
        }

        public ResultsBuilder publisher(String publisher){
            this.publisher = publisher;
            return this;
        }

        public ResultsBuilder year(String year){
            this.year = year;
            return this;
        }

        public ResultsBuilder category(String category){
            this.category = category;
            return this;
        }

        public Results build(){
            return new Results(this);
        }
    }
}