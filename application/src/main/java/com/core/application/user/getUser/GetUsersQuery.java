package com.core.application.user.getUser;

import an.awesome.pipelinr.Command;
import com.core.domain.annotations.Generated;
import com.core.domain.models.user.User;
import lombok.Getter;

import java.util.List;

@Generated
@Getter
public class GetUsersQuery implements Command<List<User>> {

	public GetUsersQuery() {

	}
}
