package engine.spectro.entity;


import engine.spectro.enums.GeneralProductEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class PhoneEntity extends ProductEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "proizvoditel")
    private String proizvoditel;

    @Column(name = "stranaProizvoditel")
    private String stranaProizvoditel;

    @Column(name = "tipPamyati")
    private String tipPamyati;

    @Column(name = "vstroennayaPamyat")
    private String vstroennayaPamyat;

    @Column(name = "operativnayaPamyat")
    private String operativnayaPamyat;

    @Column(name = "tipEkrana")
    private String tipEkrana;

    @Column(name = "chastotaObnovleniya")
    private String chastotaObnovleniya;

    @Column(name = "razmerEkrana")
    private String razmerEkrana;

    @Column(name = "razreshenieEkrana")
    private String razreshenieEkrana;

    @Column(name = "dopolnitelnyiModulKamer")
    private String dopolnitelnyiModulKamer;

    @Column(name = "osnovnoiModulKamer")
    private String osnovnoiModulKamer;

    @Column(name = "shirokougolnyiModulKamer")
    private String shirokougolnyiModulKamer;

    @Column(name = "frontalnayaKamera")
    private String frontalnayaKamera;

    @Column(name = "dinamic")
    private String dinamic;

    @Column(name = "vyhodNaushnikov")
    private String vyhodNaushnikov;

    @Column(name = "razemy")
    private String razemy;

    @Column(name = "kolichestvoSIMKart")
    private String kolichestvoSIMKart;

    @Column(name = "tipSIMKart")
    private String tipSIMKart;

    @Column(name = "nfc")
    private Boolean nfc;

    @Column(name = "zashitaOtVlagi")
    private String zashitaOtVlagi;

    @Column(name = "tipProtsessora")
    private String tipProtsessora;

    @Column(name = "kolichestvo")
    private int amount;

    @Column(name = "prise")
    private BigDecimal prise;

    @Column(name = "opisanie")
    private String opisanie;

    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private GeneralProductEnum status;

    @Column(name="image")
    private String image;

    @Override
    public String toString() {
        return "PhoneEntity{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", proizvoditel='" + proizvoditel + '\'' +
                ", stranaProizvoditel='" + stranaProizvoditel + '\'' +
                ", tipPamyati='" + tipPamyati + '\'' +
                ", vstroennayaPamyat='" + vstroennayaPamyat + '\'' +
                ", operativnayaPamyat='" + operativnayaPamyat + '\'' +
                ", tipEkrana='" + tipEkrana + '\'' +
                ", chastotaObnovleniya='" + chastotaObnovleniya + '\'' +
                ", razmerEkrana='" + razmerEkrana + '\'' +
                ", razreshenieEkrana='" + razreshenieEkrana + '\'' +
                ", dopolnitelnyiModulKamer='" + dopolnitelnyiModulKamer + '\'' +
                ", osnovnoiModulKamer='" + osnovnoiModulKamer + '\'' +
                ", shirokougolnyiModulKamer='" + shirokougolnyiModulKamer + '\'' +
                ", frontalnayaKamera='" + frontalnayaKamera + '\'' +
                ", dinamic='" + dinamic + '\'' +
                ", vyhodNaushnikov='" + vyhodNaushnikov + '\'' +
                ", razemy='" + razemy + '\'' +
                ", kolichestvoSIMKart='" + kolichestvoSIMKart + '\'' +
                ", tipSIMKart='" + tipSIMKart + '\'' +
                ", nfc=" + nfc +
                ", zashitaOtVlagi='" + zashitaOtVlagi + '\'' +
                ", tipProtsessora='" + tipProtsessora + '\'' +
                ", amount=" + amount +
                ", prise=" + prise +
                ", opisanie='" + opisanie + '\'' +
                ", status=" + status +
                ", image='" + image + '\'' +
                '}';
    }
}