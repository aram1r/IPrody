import java.util.Objects;

public class Word {

    private String word;
    private int occurrence;

    public Word(String word) {
        this.word = word;
        occurrence = 1;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public int getOccurrence() {return occurrence;}

    public void setOccurrence(int occurance) {this.occurrence = occurance;}

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        return Objects.equals(this.word, other.word);
    }

    public String toString() {
        return "{word: \"" + word + "\", occurrence: " + occurrence + "}";
    }
}
