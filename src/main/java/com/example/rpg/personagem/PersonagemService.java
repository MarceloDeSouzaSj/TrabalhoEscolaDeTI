package com.example.rpg.personagem;

import com.example.rpg.enums.TipoDoItem;
import com.example.rpg.erros.MensagemErro;
import com.example.rpg.itemMagico.ItemMagicoModel;
import com.example.rpg.itemMagico.ItemMagicoRepository;
import com.example.rpg.personagem.dto.ApresentarPersonagemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {
    @Autowired
    private PersonagemRepository personagemRepository;
    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public PersonagemModel salvar(PersonagemModel personagem) {
        if (personagem.getDefesa() + personagem.getForca() > 10)
            throw new MensagemErro("O personagem não pode ter mais de 10 pontos de atributo!");

        return personagemRepository.save(personagem);
    }

    public ApresentarPersonagemDto buscarPersonagemPorIdentificador(Long id) {
        PersonagemModel personagem = personagemRepository.findById(id).orElseThrow(() -> new MensagemErro("Personagem não encontrado"));

        ApresentarPersonagemDto response = new ApresentarPersonagemDto(personagem.getNome(), personagem.getNomeAventureiro(), personagem.getClasse(), personagem.getLevel(), personagem.getForca(), personagem.getDefesa(), personagem.getItensMagicos());
        return response;
    }

    public Long deletarPersonagem(Long id) {
        personagemRepository.deleteById(id);
        return id;
    }

    public PersonagemModel atualizarNomePorId(Long id, String nome) throws MensagemErro {
        PersonagemModel personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new MensagemErro("Personagem não encontrado!"));


        if (nome == null)
            throw new MensagemErro("Nome inválido!");

        personagem.setNome(nome);

        return personagemRepository.save(personagem);

    }

    public PersonagemModel colocarItemMagicoNoPersonagem(Long idItem, Long idPersonagem) {
        PersonagemModel personagem = personagemRepository.findById(idPersonagem).orElseThrow(() -> new MensagemErro("Personagem não encontrado"));
        ItemMagicoModel item = itemMagicoRepository.findById(idItem).orElseThrow(() -> new MensagemErro("Item não encontrado"));
        List<ItemMagicoModel> itens = personagem.getItensMagicos();

        if (item.getTipoDoItem() == TipoDoItem.Amuleto) {

            if (itens.stream().anyMatch(i -> i.getTipoDoItem() == TipoDoItem.Amuleto))
                throw new MensagemErro("O personagem não pode possuír mais de um item do tipo amuleto!");
        }

        if(item.getPersonagem() != null)
            throw new MensagemErro("O item mágico já pertence a um personagem, não é possivel vincular o item mágico a dois personagens!");

        item.setPersonagem(personagem);
        personagem.adicionarItemMagico(item);

        return personagemRepository.save(personagem);
    }

    public List<ApresentarPersonagemDto> listarPersonagem(){
        List<PersonagemModel> personagens =  personagemRepository.findAll();
        if(personagens.isEmpty())
            throw new MensagemErro("Nenhum personagem encontrado!");

        return personagens.stream()
                .map(personagem -> new ApresentarPersonagemDto(
                        personagem.getNome(),
                        personagem.getNomeAventureiro(),
                        personagem.getClasse(),
                        personagem.getLevel(),
                        personagem.getForca(),
                        personagem.getDefesa(),
                        personagem.getItensMagicos()
                ))
                .collect(Collectors.toList());
    }

    public String RemoverItemMagicoDoPersonagem(Long idItem, Long idPersonagem){
        PersonagemModel personagem = personagemRepository.findById(idPersonagem).orElseThrow(() -> new MensagemErro("Personagem não encontrado"));
        ItemMagicoModel item = itemMagicoRepository.findById(idItem).orElseThrow(() -> new MensagemErro("Item não encontrado"));

        ItemMagicoModel itemParaRemover = personagem.getItensMagicos().stream()
                .filter(itemMagico -> itemMagico.getIdentificador().equals(idItem))
                .findFirst()
                .orElseThrow(() -> new MensagemErro("Item mágico não encontrado para este personagem"));

        itemParaRemover.setPersonagem(null);
        personagem.getItensMagicos().remove(itemParaRemover);


        personagemRepository.save(personagem);
        itemMagicoRepository.save(itemParaRemover);

        return "Item removido com sucesso!";
    }

    public List<ItemMagicoModel> listarItemMagicoDoPersonagem(Long idPersonagem){
        PersonagemModel personagem = personagemRepository.findById(idPersonagem).orElseThrow(() -> new MensagemErro("Personagem não encontrado"));

        return personagem.getItensMagicos();
    }

    public ItemMagicoModel buscarAmuleto(Long idPersonagem){
        PersonagemModel personagem = personagemRepository.findById(idPersonagem).orElseThrow(() -> new MensagemErro("Personagem não encontrado"));

        ItemMagicoModel amuleto = personagem.getItensMagicos().stream()
                .filter(item -> item.getTipoDoItem() == TipoDoItem.Amuleto)
                .findFirst()
                .orElseThrow(() -> new MensagemErro("Personagem não possui amuleto"));


        return amuleto;
    }
}
