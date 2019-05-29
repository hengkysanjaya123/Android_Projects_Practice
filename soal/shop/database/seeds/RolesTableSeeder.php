<?php
declare(strict_types=1);

use Illuminate\Database\Seeder;
use Pandawa\Component\Ddd\Repository\EntityManagerInterface;
use Pandawa\Component\Ddd\Repository\RepositoryInterface;
use function Pandawa\Reactive\fromArray;
use function Pandawa\Reactive\map;
use function Pandawa\Reactive\tap;
use Shop\User\Model\Role;

/**
 * Created by IntelliJ IDEA.
 * User: komalasurya
 * Date: 18/12/18
 * Time: 00.47
 */

final class RolesTableSeeder extends Seeder
{
    /**
     * @var array
     */
    private $data = [
        ['name' => 'admin'],
        ['name' => 'customer'],
    ];

    public function run(): void
    {
        fromArray($this->data)
            ->pipe(
                map(function (array $item) {
                    return new Role($item);
                }),
                tap(function (Role $role) {
                    $this->repo()->save($role);
                })
            )
            ->subscribe();
    }

    /**
     * @return RepositoryInterface
     */
    private function repo(): RepositoryInterface
    {
        return app(EntityManagerInterface::class)->getRepository(Role::class);
    }
}