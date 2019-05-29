<?php
/**
 * Created by IntelliJ IDEA.
 * User: komalasurya
 * Date: 18/12/18
 * Time: 09.23
 */

namespace Shop\User\Command;


use function Pandawa\Reactive\map;
use function Pandawa\Reactive\tap;
use function Pandawa\Reactive\of;
use Shop\User\Model\User;
use Shop\User\Repository\RoleRepository;
use Shop\User\Repository\UserRepository;

final class RegisterUserHandler
{
    /**
     * @var UserRepository
     */
    private $repository;

    /**
     * @var User
     */
    private $result;
    /**
     * @var RoleRepository
     */
    private $roleRepository;

    /**
     * RegisterUserHandler constructor.
     * @param UserRepository $repository
     * @param RoleRepository $roleRepository
     */
    public function __construct(UserRepository $repository, RoleRepository $roleRepository)
    {
        $this->repository = $repository;
        $this->roleRepository = $roleRepository;
    }

    public function handle(RegisterUser $message)
    {
        of($message)->pipe(
            map(function (RegisterUser $message) {
                return [$message->payload()->all(),
                    $this->roleRepository->findByNames(['customer'])
                ];
            }),
            map(function (array $params) {
                list($payload, $role) = $params;

                return new User(array_merge($payload,[
                    'role_id' => $role[0]->id
                ]));
            }),
            tap(function (User $user) {
                $this->repository->save($user);
            })
        )->subscribe(function (User $user) {
            $this->result = $user;
        });
        return $this->result;
    }
}