package co.edu.poli.aplicacion.repositorio;

import co.edu.poli.aplicacion.services.ConexionSupabase;
import co.edu.poli.aplicacion.modelo.Pais;
import co.edu.poli.aplicacion.modelo.Pasaporte;
import co.edu.poli.aplicacion.modelo.PasaporteDiplomatico;
import co.edu.poli.aplicacion.modelo.PasaporteOrdinario;
import co.edu.poli.aplicacion.modelo.Titular;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperacionesPasaporte implements Filtro<Pasaporte> {

    @Override
    public String insertar(Pasaporte pasaporte) {
        String sqlPasaporte = "INSERT INTO bdpasaporte (pasaporteid, fechaexp, titular, pais) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionSupabase.getInstance().getConnection()) {

            conn.setAutoCommit(false);

            if (pasaporte instanceof PasaporteOrdinario) {
                String sqlOrdinario = "INSERT INTO pasaporteordinario (idpasaporte, tipopasaporte, descripcion) VALUES (?, ?, ?)";

                try (PreparedStatement pstmt1 = conn.prepareStatement(sqlPasaporte); PreparedStatement pstmt2 = conn.prepareStatement(sqlOrdinario)) {

                    pstmt1.setString(1, pasaporte.getId());
                    pstmt1.setString(2, pasaporte.getFechaExp());
                    pstmt1.setString(3, pasaporte.getTitular() != null ? pasaporte.getTitular().getNombre() : null);
                    pstmt1.setString(4, pasaporte.getPais() != null ? pasaporte.getPais().getNombre() : null);
                    pstmt1.executeUpdate();

                    PasaporteOrdinario ordinario = (PasaporteOrdinario) pasaporte;
                    pstmt2.setString(1, ordinario.getId());
                    pstmt2.setString(2, ordinario.getTipoPasaporte());
                    pstmt2.setString(3, ordinario.getDescripcion());
                    pstmt2.executeUpdate();

                    conn.commit();
                    return "Pasaporte ordinario insertado correctamente.";

                } catch (SQLException e) {
                    conn.rollback();
                    return "Error al insertar ordinario (rollback): " + e.getMessage();
                }
            } else if (pasaporte instanceof PasaporteDiplomatico) {
                String sqlDiplomatico = "INSERT INTO pasaportediplomatico (idpasaporte, tipopasaporte, descripcion) VALUES (?,?,?)";

                try (PreparedStatement pstmt1 = conn.prepareStatement(sqlPasaporte); PreparedStatement pstmt2 = conn.prepareStatement(sqlDiplomatico)) {

                    pstmt1.setString(1, pasaporte.getId());
                    pstmt1.setString(2, pasaporte.getFechaExp());
                    pstmt1.setString(3, pasaporte.getTitular() != null ? pasaporte.getTitular().getNombre() : null);
                    pstmt1.setString(4, pasaporte.getPais() != null ? pasaporte.getPais().getNombre() : null);
                    pstmt1.executeUpdate();

                    PasaporteDiplomatico diplom = (PasaporteDiplomatico) pasaporte;
                    pstmt2.setString(1, diplom.getId());
                    pstmt2.setString(2, diplom.getTipoPasaporte());
                    pstmt2.setString(3, diplom.getDescripcion());
                    pstmt2.executeUpdate();

                    conn.commit();
                    return "Pasaporte diplomático insertado correctamente.";

                } catch (SQLException e) {
                    conn.rollback();
                    return "Error al insertar diplomático (rollback): " + e.getMessage();
                }
            }

        } catch (SQLException e) {
            return "Error de conexión: " + e.getMessage();
        }

        return null;
    }

    @Override
    public String eliminar(String pasaporteId) {

        String sql = "DELETE FROM pasaportediplomatico WHERE idpasaporte = ?;\n"
                + "DELETE FROM pasaporteordinario WHERE idpasaporte = ?;\n"
                + "DELETE FROM bdpasaporte WHERE pasaporteid = ?;";

        try (Connection conn = ConexionSupabase.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pasaporteId);
            stmt.setString(2, pasaporteId);
            stmt.setString(3, pasaporteId);

            stmt.executeUpdate();

            return " Registro eliminado correctamente.";

        } catch (SQLException e) {
            return (" Error al eliminar: " + e.getMessage());
        }

    }

    @Override
    public Pasaporte selectId(String pasaporteId) {
        Pasaporte pasaporteBuscado = null;

        String sqlBase = "SELECT * FROM bdpasaporte WHERE pasaporteid = ?";
        String sqlOrdinario = "SELECT * FROM pasaporteordinario WHERE idpasaporte = ?";
        String sqlDiplomatico = "SELECT * FROM pasaportediplomatico WHERE idpasaporte = ?";

        try (Connection conn = ConexionSupabase.getInstance().getConnection()) {
            // Primero buscamos en la tabla base
            try (PreparedStatement pstmt = conn.prepareStatement(sqlBase)) {
                pstmt.setString(1, pasaporteId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String id = rs.getString("pasaporteid");
                    String fechaExp = rs.getString("fechaexp");
                    String titular = rs.getString("titular");
                    String pais = rs.getString("pais");

                    // Ahora miramos si existe en Ordinario
                    try (PreparedStatement pstmtO = conn.prepareStatement(sqlOrdinario)) {
                        pstmtO.setString(1, id);
                        ResultSet rsO = pstmtO.executeQuery();
                        if (rsO.next()) {
                            String tipoPasaporte = rsO.getString("tipopasaporte");
                            String motivo = rsO.getString("descripcion");
                            pasaporteBuscado = new PasaporteOrdinario(id, fechaExp, null, null, tipoPasaporte, motivo);
                            return pasaporteBuscado; // devolvemos directamente
                        }
                    }

                    // Si no era ordinario, miramos si existe en Diplomático
                    try (PreparedStatement pstmtD = conn.prepareStatement(sqlDiplomatico)) {
                        pstmtD.setString(1, id);
                        ResultSet rsD = pstmtD.executeQuery();
                        if (rsD.next()) {
                            String tipoPasaporte = rsD.getString("tipopasaporte");
                            String mision = rsD.getString("descripcion");
                            pasaporteBuscado = new PasaporteDiplomatico(id, fechaExp, null, null, tipoPasaporte, mision);
                            return pasaporteBuscado;
                        }
                    }

                    // Si no estaba en ninguna tabla hija, devolvemos el genérico
                    pasaporteBuscado = new Pasaporte(id, fechaExp, null, null) {
                    };
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }

        return pasaporteBuscado;
    }

    @Override
    public String actualizar(Pasaporte pasaporte) {

        String sqlPasaporte = "UPDATE bdpasaporte SET fechaexp=?, titular=?, pais=? WHERE pasaporteid=?";

        // Sentencias específicas según tipo
        String sqlOrdinario = "UPDATE pasaporteordinario SET tipopasaporte=?, descripcion=? WHERE idpasaporte=?";
        String sqlDiplomatico = "UPDATE pasaportediplomatico SET tipopasaporte=?, descripcion=? WHERE idpasaporte=?";

        try (Connection conn = ConexionSupabase.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement pstmt1 = conn.prepareStatement(sqlPasaporte); PreparedStatement pstmt2 = pasaporte instanceof PasaporteOrdinario
                    ? conn.prepareStatement(sqlOrdinario)
                    : conn.prepareStatement(sqlDiplomatico)) {
                // Actualizar tabla bdpasaporte
                pstmt1.setString(1, pasaporte.getFechaExp());
                pstmt1.setString(2, pasaporte.getTitular() != null ? pasaporte.getTitular().getNombre() : null);
                pstmt1.setString(3, pasaporte.getPais() != null ? pasaporte.getPais().getNombre() : null);
                pstmt1.setString(4, pasaporte.getId());
                pstmt1.executeUpdate();

                // Actualizar tabla específica según tipo
                if (pasaporte instanceof PasaporteOrdinario) {
                    PasaporteOrdinario ordinario = (PasaporteOrdinario) pasaporte;
                    pstmt2.setString(1, ordinario.getTipoPasaporte());
                    pstmt2.setString(2, ordinario.getDescripcion());
                    pstmt2.setString(3, pasaporte.getId());
                } else if (pasaporte instanceof PasaporteDiplomatico) {
                    PasaporteDiplomatico diplom = (PasaporteDiplomatico) pasaporte;
                    pstmt2.setString(1, diplom.getTipoPasaporte());
                    pstmt2.setString(2, diplom.getDescripcion());
                    pstmt2.setString(3, pasaporte.getId());
                }

                pstmt2.executeUpdate();

                conn.commit();
                return pasaporte instanceof PasaporteOrdinario
                        ? "Pasaporte ordinario actualizado correctamente."
                        : "Pasaporte diplomático actualizado correctamente.";

            } catch (SQLException e) {
                conn.rollback();
                return "Error al actualizar (rollback): " + e.getMessage();
            }

        } catch (SQLException e) {
            return "Error de conexión: " + e.getMessage();
        }
    }

    @Override
    public ArrayList<Pasaporte> selectAll() {
        String sql = "SELECT p.*, d.tipopasaporte,o.tipopasaporte, d.descripcion, o.descripcion\n"
                + "FROM bdpasaporte p\n"
                + "LEFT JOIN pasaportediplomatico d ON p.pasaporteid = d.idpasaporte\n"
                + "LEFT JOIN pasaporteordinario o ON p.pasaporteid = o.idpasaporte;";
        ArrayList<Pasaporte> lista = new ArrayList<>();

        try (Connection conn = ConexionSupabase.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("pasaporteid");
                String fechaExp = rs.getString("fechaexp");
                String titular = rs.getString("titular");
                String pais = rs.getString("pais");

                Pasaporte pasaporte;

                if (rs.getString("tipopasaporte").equals("Diplomatico")) { // Diplomático
                    String tipoPasaporte = rs.getString("tipopasaporte");
                    String mision = rs.getString("descripcion");
                    pasaporte = new PasaporteDiplomatico(id, fechaExp, null, null, tipoPasaporte, mision);
                } else {
                    String tipoPasaporte = rs.getString("tipopasaporte");
                    String motivo = rs.getString("descripcion");
                    pasaporte = new PasaporteOrdinario(id, fechaExp, null, null, tipoPasaporte, motivo);
                }

                lista.add(pasaporte);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public ArrayList<Pasaporte> filterId(String ident) {

        ArrayList<Pasaporte> pasaportes_filtrados = new ArrayList<>();
        String sql = "SELECT * FROM bdpasaporte WHERE pasaporteid LIKE ?";

        try (Connection conn = ConexionSupabase.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + ident + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Pasaporte pasaporte = new Pasaporte(
                        rs.getString("pasaporteid"),
                        rs.getString("fechaexp"),
                        null, null) {
                };

                pasaportes_filtrados.add(pasaporte);
            }

        } catch (SQLException e) {
            System.out.println(" Error al leer: " + e.getMessage());
        }
        return pasaportes_filtrados;

    }

}
