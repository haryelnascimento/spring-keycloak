package io.imob.api.v1.controller;

import io.imob.api.v1.assembler.PropertyModelAssembler;
import io.imob.api.v1.assembler.PropertyModelDissembler;
import io.imob.api.v1.model.PropertyInput;
import io.imob.api.v1.model.PropertyOutput;
import io.imob.domain.filter.PropertyFilter;
import io.imob.domain.model.Property;
import io.imob.domain.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/properties")
@Tag(name = "Propriedades", description = "Serviços para gerenciamento de propriedades")
public class PropertyController {

    private final PropertyService propertyService;
    private final PropertyModelDissembler propertyModelDissembler;
    private final PropertyModelAssembler propertyModelAssembler;

    @Operation(summary = "Busca de propriedades por filtros", method = "GET")
    @ApiResponse(responseCode = "200", description = "Propriedades encontradas")
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping
    public Page<PropertyOutput> search(
            PropertyFilter filter,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sort,
            @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort);
        return propertyService.search(filter, pageable).map(propertyModelAssembler::toModel);
    }


    @Operation(summary = "Busca de propriedade por ID", method = "GET")
    @ApiResponse(responseCode = "200", description = "Propriedade encontrada")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Propriedade não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping("/{id}")
    public PropertyOutput findById(@PathVariable("id") UUID id) {
        return propertyModelAssembler.toModel(propertyService.findById(id));
    }


    @Operation(summary = "Cadastro de propriedade", method = "POST")
    @ApiResponse(responseCode = "201", description = "Propriedade cadastrada")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping
    public PropertyOutput save(@RequestBody @Valid PropertyInput input) {
        Property property = propertyModelDissembler.toDomain(input);
        return propertyModelAssembler.toModel(propertyService.save(property));
    }


    @Operation(summary = "Atualização de propriedade", method = "PUT")
    @ApiResponse(responseCode = "200", description = "Propriedade atualizada")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Propriedade não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PutMapping("/{id}")
    public PropertyOutput update(@PathVariable UUID id, @RequestBody PropertyInput input) {
        Property property = propertyModelDissembler.toDomain(input);
        return propertyModelAssembler.toModel(propertyService.update(id, property));
    }


    @Operation(summary = "Exclusão de propriedade", method = "DELETE")
    @ApiResponse(responseCode = "204", description = "Propriedade excluída")
    @ApiResponse(responseCode = "400", description = "ID inválido")
    @ApiResponse(responseCode = "404", description = "Propriedade não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        propertyService.delete(id);
    }

}
