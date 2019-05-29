<?php
/**
 * Created by IntelliJ IDEA.
 * User: komalasurya
 * Date: 18/12/18
 * Time: 00.46
 */

namespace Shop\User\Repository;


use Pandawa\Component\Ddd\Collection;
use Pandawa\Component\Ddd\Repository\Repository;

class RoleRepository extends Repository
{
    /**
     * @param array $names
     *
     * @return Collection
     */
    public function findByNames(array $names): Collection
    {
        return $this->execute($this->createQueryBuilder()->whereIn('name', $names));
    }
}