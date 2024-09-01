package example.cashcard;

//import org.h2.mvstore.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.net.URI;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;

@RestController
@RequestMapping("/cashcards")
 class CashCardController {
    private final CashCardRepository cashCardRepository;

    private CashCardController(CashCardRepository cashCardRepository){
        this.cashCardRepository = cashCardRepository;
    }

    private CashCard findCashCard(Long requestedId, Principal principal){
        return cashCardRepository.findByIdAndOwner(requestedId, principal.getName());
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<CashCard> findById(@PathVariable Long requestedId, Principal principal) {
        CashCard cashCard = findCashCard(requestedId, principal);


        if (cashCard != null){
            return ResponseEntity.ok(cashCard);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    

    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb, Principal principal){
        CashCard cashCardWithOwner = new CashCard(null, newCashCardRequest.amount(), principal.getName());

        CashCard savedCashCard = cashCardRepository.save(cashCardWithOwner);
        URI locationOfNewCashCard = ucb.path("cashcards/{id}")
        .buildAndExpand(savedCashCard.id()).toUri();


        return ResponseEntity.created(locationOfNewCashCard).build();
    }

   
    @GetMapping
    private ResponseEntity <List<CashCard>> findAll (Pageable pageable, Principal principal){
        org.springframework.data.domain.Page<CashCard> page = cashCardRepository.findByOwner(principal.getName(),
            PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
            ));
            return ResponseEntity.ok(page.getContent());
    }

    @PutMapping("/{requestId}")
    private ResponseEntity<Void> putCashCard(@PathVariable Long requestId, @RequestBody CashCard cashCardUpdate
    ,Principal principal){
        
        CashCard cashCard = findCashCard(requestId, principal);

        if (cashCard != null){

            CashCard updatedCashCard = new CashCard(cashCard.id(),
            cashCardUpdate.amount(), principal.getName());
        
        cashCardRepository.save(updatedCashCard);

        return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteCashCard(@PathVariable Long id, Principal principal){

        if(cashCardRepository.existsByIdAndOwner(id, principal.getName())){
            cashCardRepository.deleteById(id); 
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.
        notFound().build();

      

        
    }



}