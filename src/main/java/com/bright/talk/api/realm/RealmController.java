package com.bright.talk.api.realm;

import com.bright.talk.exception.BadRequestException;
import com.bright.talk.exception.Message;
import com.bright.talk.exception.ResourceNotFoundException;
import com.bright.talk.domain.Realm;
import com.bright.talk.domain.RealmService;
import io.swagger.annotations.ApiOperation;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.Valid;

@Controller
@RequestMapping(path="/service/user/realm")
public class RealmController {

	private RealmService realmService;

	private Mapper mapper;

	/**
	 * Constructor.
	 *
	 * @param realmService  realm service
	 * @param mapper		object mapper
	 */
	public RealmController(
		RealmService realmService,
		Mapper mapper
	) {
		this.realmService = realmService;
		this.mapper = mapper;
	}

	/**
	 * Returns realm by realmId.
	 *
	 * @throws BadRequestException when realmId isn't numeric
	 * @throws ResourceNotFoundException when realm not found
	 * @return RealmResponse
	 */
	@ApiOperation(value = "Returns realm by realmId")
	@GetMapping(value = "/{realmId}")
	@ResponseBody
	public RealmResponse get(
		@PathVariable(name = "realmId") String realmIdStr
	) throws BadRequestException, ResourceNotFoundException {
		try {
			Long realmId = Long.valueOf(realmIdStr);
			Realm realm = realmService.get(realmId);
			return mapper.map(realm, RealmResponse.class);
		} catch (NumberFormatException e) {
			throw new BadRequestException(Message.INVALID_ARGUMENT.getText());
		}
	}


	/**
	 * Creates realm.
	 *
	 * @param request include data about user realm
	 * @throws BadRequestException when realm name isn't supplied or empty
	 * @throws BadRequestException when realm with the same name currently exist
	 * @return RealmResponse
	 */
	@ApiOperation(value = "Creates realm")
	@PostMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public RealmResponse create(
		@RequestBody @Valid RealmCreateRequest request
	) throws BadRequestException {
		String name = request.getName();
		if (name == null || name.trim().isEmpty()) {
			throw new BadRequestException(Message.INVALID_REALM_NAME.getText());
 		}
 		Realm realm = realmService.create(name, request.getDescription());
		return mapper.map(realm, RealmResponse.class);
	}
}
