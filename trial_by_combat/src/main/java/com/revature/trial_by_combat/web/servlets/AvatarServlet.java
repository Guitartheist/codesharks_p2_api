package com.revature.trial_by_combat.web.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.revature.trial_by_combat.models.Avatar;
import com.revature.trial_by_combat.services.AvatarService;
import com.revature.trial_by_combat.services.PlayerService;

@RestController
@RequestMapping("/avatar")
public class AvatarServlet {
	private final AvatarService avatarService;
	private final PlayerService playerService;
	
	@Autowired
	public AvatarServlet(AvatarService avatarService, PlayerService playerService) {
		this.avatarService = avatarService;
		this.playerService = playerService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Avatar createAvatar(@RequestBody Avatar avatar) {
		avatar.setPlayer( playerService.findPlayerById(avatar.getPlayer().getId()).get() );
		return avatarService.createNewAvatar(avatar);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Avatar findAvatarById(@RequestParam int id) {
		return avatarService.findAvatarById(id).get();
	}
	
	@GetMapping("/player")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Avatar> findAvatarsByPlayerId(@RequestParam int id) {
		return avatarService.findAvatarsByPlayerId(id);
	}
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Iterable<Avatar> findAllAvatars() {
		return avatarService.findAllAvatars();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Avatar updateAvatar(@RequestBody Avatar avatar) {
		avatar.setPlayer( playerService.findPlayerById(avatar.getPlayer().getId()).get() );
		return avatarService.updateAvatar(avatar);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteAvatar(@RequestParam int id) {
		avatarService.deleteAvatar(id);
	}
}
