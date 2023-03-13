package euclid;

public class Results{
    private int number;
    private int score = 1;
    private String name;
    private String author;
    private String publisher = "Άγνωστος";
    private String year = "-";
    private String category = "-";
    
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
        if (!this.author.isEmpty())
            return this.author;
        return "-";
    }
    
    public String getPublisher(){
        if (!this.publisher.isEmpty())
            return this.publisher;
        return "-";
    }
    
    public String getYear(){
        if(!this.year.isEmpty())
            return this.year;
        return "-";
    }
    
    public String getCategory(){
        if(!this.category.isEmpty())
            return this.category;
        return "-";
    }
    
    public int getScore(){
        return this.score;
    }
    public void incrScore(){
        this.score++;
    }
    
    
    @Override
    public String toString(){
        String result = "Number: "+this.number+
                        "\nName: "+this.name+
                        "\nAuthor: "+this.author+
                        "\nPublisher: "+this.publisher+
                        "\nYear: "+this.year+
                        "\nCategory: "+this.category+
                        "\nScore: "+this.score;
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