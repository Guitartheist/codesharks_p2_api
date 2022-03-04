package com.revature.trial_by_combat.web.servlets;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.trial_by_combat.models.Loadout;
import com.revature.trial_by_combat.services.PlayerService;
import com.revature.trial_by_combat.services.LoadoutService;

@RestController
@RequestMapping("/loadout")
public class LoadoutServlet {
	private final LoadoutService loadoutService;
	private final PlayerService playerService;
	
	@Autowired
	public LoadoutServlet(LoadoutService loadoutService, PlayerService playerService) {
		this.loadoutService = loadoutService;
		this.playerService = playerService;
	}

/*** Create ***/	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Loadout createLoadout(@RequestBody Loadout loadout) {
		loadout.setPlayer( playerService.findPlayerById(loadout.getPlayer().getId()) .get() );
		return loadoutService.createNewLoadout(loadout);
	}
	
	
/*** Read ***/
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Loadout> findAllLoadouts() {
		return loadoutService.findAllLoadouts();
	}
	
	@GetMapping("/player")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Loadout> findLoadoutByPlayerId(@RequestParam int id) {
		return loadoutService.findLoadoutsByPlayerId(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Loadout findLoadoutById(@RequestParam int id ) {
		return loadoutService.findLoadoutById(id).get();
	}
	
	
/*** Update ***/
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Loadout updateLoadout(@RequestBody Loadout loadout) {
		Loadout loadoutToUpdate = loadoutService.findLoadoutById(loadout.getId()).get();
		loadoutToUpdate.setPlayer( playerService.findPlayerById(loadout.getPlayer().getId()).get() );
		loadoutToUpdate.setName(loadout.getName());
		loadoutToUpdate.setDescription(loadout.getDescription());
		
		return loadoutService.updateLoadout(loadoutToUpdate);
	}
	
/*** Delete ***/
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteLoadout(@RequestParam int id) {
		loadoutService.deleteLoadoutById(id);
	}
}



