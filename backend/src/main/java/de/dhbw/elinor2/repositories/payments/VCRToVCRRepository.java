package de.dhbw.elinor2.repositories.payments;

import de.dhbw.elinor2.entities.VCRToVCR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VCRToVCRRepository extends JpaRepository<VCRToVCR, UUID>
{
}