package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IpGenerada {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer ip;
    private Integer idNumero;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
    }
    
    public Integer getIdNumero() {
        return idNumero;
    }

    public void setIdNumero(Integer idNumero) {
        this.idNumero = idNumero;
    }
    
    
}