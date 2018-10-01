package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class IpGenerada {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String ip;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Numero numero;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public Numero getIdNumero() {
        return numero;
    }

    public void setIdNumero(Numero numero) {
        this.numero = numero;
    }
    
    
}