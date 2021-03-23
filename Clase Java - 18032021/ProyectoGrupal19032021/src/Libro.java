public class Libro {

    public String titulo;
    public int id;
    public boolean estado;

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String autor;

    public Libro() {
    }


    public Libro libro( String autor, String titulo, int id, boolean estado) {
        this.autor = autor;
        this.estado = estado;
        this.titulo = titulo;
        this.id = id;
        return libro(this.autor,this.titulo,this.id,this.estado);
    }


    @Override
    public String toString() {
        return titulo+" , "+ id+ " , "+ autor +", "+ estado;
    }

    public void prestamo(){
        this.estado = true;
    }

    public void devolucion(){
        this.estado=false;
    }

}
