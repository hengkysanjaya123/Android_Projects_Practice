<?php
/**
 * This file is part of the Pandawa package.
 *
 * (c) 2018 Pandawa <https://github.com/bl4ckbon3/pandawa>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

declare(strict_types=1);

namespace Pandawa\Component\Ddd\Relationship;

use Illuminate\Database\Eloquent\Relations\HasOne as LaravelHasOne;
use Pandawa\Component\Ddd\AbstractModel;

/**
 * @author  Iqbal Maulana <iq.bluejack@gmail.com>
 */
class HasOne extends LaravelHasOne
{
    use EntityManagerPersistentTrait;

    /**
     * @var AbstractModel
     */
    protected $parent;

    /**
     * @param AbstractModel $model
     */
    public function associate(AbstractModel $model): void
    {
        $this->parent->addBeforeAction(
            function () use ($model) {
                $this->setForeignAttributesForCreate($model);
                $this->persist($model);
            }
        );
    }
}
