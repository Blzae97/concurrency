package com.blaze.mutex.domain.character.doll.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@Getter
@Table(name = "character_doll_inventory")
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class CharacterDollInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_doll_inventory_id", columnDefinition = "int(11)")
    private Long characterDollInventoryId;

    @Column(name = "character_doll_name", columnDefinition = "varchar(100)")
    private String characterDollName;

    @Column(name = "characterDollCount", columnDefinition = "int(11)")
    private Long characterDollCount;

    @Builder
    public CharacterDollInventory(Long characterDollInventoryId, String characterDollName, Long characterDollCount) {
        this.characterDollInventoryId = characterDollInventoryId;
        this.characterDollName = characterDollName;
        this.characterDollCount = characterDollCount;
    }
}
