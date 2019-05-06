import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Valute implements Serializable {
    private static final long serialVersionUID = 6035082410564996797L;

    private LocalDate date;
    private String type;
    private String code;
    private String nominal;
    private String name;
    private BigDecimal value;

    public Valute() {
    }

    public Valute(LocalDate date, String type, String code, String nominal, String name, BigDecimal value) {
        this.date = date;
        this.type = type;
        this.code = code;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Valute{" +
                "date=" + date +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", nominal='" + nominal + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
