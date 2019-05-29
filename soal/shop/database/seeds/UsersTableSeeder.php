<?php
declare(strict_types=1);

use Illuminate\Database\Seeder;
use Pandawa\Component\Ddd\Collection;
use Pandawa\Component\Ddd\Repository\EntityManagerInterface;
use Pandawa\Component\Ddd\Repository\RepositoryInterface;
use Rx\Observable;
use function Pandawa\Reactive\flatMap;
use function Pandawa\Reactive\forkJoin;
use function Pandawa\Reactive\fromArray;
use function Pandawa\Reactive\fromCollection;
use function Pandawa\Reactive\map;
use function Pandawa\Reactive\of;
use function Pandawa\Reactive\pluck;
use function Pandawa\Reactive\tap;
use Shop\User\Model\Role;
use Shop\User\Model\User;
use Shop\User\Repository\RoleRepository;

/**
 * @author  Iqbal Maulana <iq.bluejack@gmail.com>
 */
final class UsersTableSeeder extends Seeder
{
    /**
     * @var array
     */
    private $data = [
//        [
//            'id'           => 'f14f1d11-fe84-4995-8966-6a529f5c3001',
////            'email'        => 'root@naker.go.id',
//            'username'     => 'root',
//            'name'         => 'Administrator',
//            'roles'        => ['admin'],
////            'protected'    => true,
//            'password'     => 'secret',
//        ],
//        [
//            'id'           => '3bbf4b32-65f6-44c9-8ec0-ed375e207675',
////            'email'        => 'silentrazgriz@gmail.com',
//            'username'     => '3172020809920009',
//            'name'         => 'Endy Gower',
////            'roles'        => ['agency'],
////            'protected'    => true,
//            'password'     => 'secret',
//        ],
        [
            'id'           => 'e3e3c309-b4c2-49ea-8faf-84bee751542b',
//            'email'        => 'komala_surya95@yahoo.com',
            'username'     => 'komalasurya',
            'name'         => 'Komala Surya',
            'roles'        => ['customer'],
//            'protected'    => true,
            'password'     => 'secret',
        ],
        [
            'id'           => '49362587-76f4-4345-96cb-040c69fc954b',
//            'email'        => 'valentino.ekaputra@live.com',
            'username'     => 'valentinoekaputra',
            'name'         => 'Valentino Ekaputra',
            'roles'        => ['admin'],
//            'protected'    => true,
            'password'     => 'secret',
        ],
//        [
//            'id'           => 'e57b75ac-b8ba-46c2-9297-b903c30b1698',
////            'email'        => 'vandorohery99@gmail.com',
//            'username'     => '1471110612980002',
//            'name'         => 'Hery Vandoro',
////            'roles'        => ['apprentice'],
//            'password'     => 'secret',
//        ]
    ];

    public function run(): void
    {
        fromArray($this->data)
            ->pipe(
                flatMap(function (array $item) {
                    return forkJoin([$this->getUser($item), $this->getRoles($item['roles'])]);
                }),
                map(function (array $results) {
                    list($user, $roles) = $results;

//                    $user->role()->associate($roles[0]);
                    $user->role_id = $roles;

                    return $user;
                }),
                tap(function (User $user) {
                    $this->userRepo()->save($user);
                })
            )
            ->subscribe();
    }

    /**
     * @param array $roles
     *
     * @return Observable
     */
    private function getRoles(array $roles): Observable
    {
        return of($roles)->pipe(
            map(function (array $items) {
                return $this->roleRepo()->findByNames($items);
            }),
            flatMap(function (Collection $roles) {
                return fromCollection($roles)->pipe(
                    map(function (Role $role) {
                        return $role->toArray();
                    }),
                    pluck('id')
                );
            })
        );
    }

    /**
     * @param array $user
     *
     * @return Observable
     */
    private function getUser(array $user): Observable
    {
        return of($user)->pipe(
            map(function (array $item) {
                return array_except($item, 'roles');
            }),
//            map(function (array $item) {
//                return array_merge($item, ['status' => UserStatus::ACTIVE]);
//            }),
            map(function (array $item) {
                return new User($item);
            })
        );
    }

    /**
     * @return RepositoryInterface
     */
    private function userRepo(): RepositoryInterface
    {
        return app(EntityManagerInterface::class)->getRepository(User::class);
    }

    /**
     * @return RoleRepository
     */
    private function roleRepo(): RoleRepository
    {
        return app(RoleRepository::class);
    }
}
