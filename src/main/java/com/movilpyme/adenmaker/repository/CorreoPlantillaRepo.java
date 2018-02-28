package com.movilpyme.adenmaker.repository;

import com.movilpyme.adenmaker.domain.Correo;
import com.movilpyme.adenmaker.domain.CorreoPlantilla;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorreoPlantillaRepo extends CrudRepository<CorreoPlantilla, Long> {

    public List<CorreoPlantilla> findAllByNombre (String nombre);

    public List<CorreoPlantilla> findAllByCorreoByIdCorreo(Correo correo);

    public CorreoPlantilla findByCorreoByIdCorreoAndNombre(Correo correo, String nombre);
}
